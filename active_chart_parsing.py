#!/usr/bin/python
# -*- coding : utf-8 -*-

# Thanks for the great reference from:
# http://cs.union.edu/~striegnk/courses/nlp-with-prolog/html/node66.html

class Rule:
	left = ""
	right = []
	def __init__(self, left, right):
		self.left = left
		self.right = right
	def __str__(self):
		return "<%s, %s>" % (self.left, self.right)

class Item:
	active = True
	i = 0
	j = 0
	dot = 0
	left = ""
	right = []
	def __init__(self, active, i, j, dot, left, right):
		self.active = active
		self.i = i
		self.j = j
		self.dot = dot
		self.left = left
		self.right = right
	def __str__(self):
		return "<%s, i=%d, j=%d, dot=%d, %s, %s>" % (self.active, self.i, self.j, self.dot, self.left, self.right)

#
#	0  mia  1  danced  2
#
#	    0  1  2
#	0  [] [] []
#	1  [] [] []
#	2  [] [] []
#
def debug(st, chart, grammar):
	print "=================="
	for g in grammar:
		print g
	print "------------------"
	for item in st[:: -1]:
		print item
	print "------------------"
	for i in range(len(chart)):
		for j in range(len(chart[i])):
			print i, j, ":",
			for k in chart[i][j]:
				print k, "|||", 
			print
	print "================================="

def parse(grammar, sent):
	st = [Item(False, 1, 2, 1, "IV", ["danced"]), Item(False, 0, 1, 1, "PN", ["mia"])]
	chart = [ [ [] for j in range(len(sent) + 1)] for i in range(len(sent) + 1)]

	debug(st, chart, grammar)

	while len(st) > 0:
		# step 1-2: pop and add to chart
		item = st.pop()
		if item not in chart[item.i][item.j]:
			chart[item.i][item.j].append(item)
		else:
			continue
		# step 3-4: combine and generate new
		if item.active: # active item
			# step 3: combine, look for a right passive item
			anchor = item.right[item.dot]
			for colIndex in range(len(chart[item.j])):
				for itemAfter in chart[item.j][colIndex]:
					if not itemAfter.active and anchor == itemAfter.left:
						if item.dot == len(item.right) - 1:
							st.append(Item(False, item.i, itemAfter.j, item.i + 1, item.left, item.right))
						else:
							st.append(Item(True, item.i, itemAfter.j, item.i + 1, item.left, item.right))
		else: # passive item
			# step 3: combine, look for a left active item
			anchor = item.left
			for rowIndex in range(len(chart)):
				for itemBefore in chart[rowIndex][item.i]:
					if itemBefore.active and itemBefore.right[itemBefore.dot] == anchor:
						if itemBefore.dot == len(itemBefore.right) - 1:
							st.append(Item(False, itemBefore.i, item.j, itemBefore.i + 1, itemBefore.left, itemBefore.right))
						else:
							st.append(Item(True, itemBefore.i, item.j, itemBefore.i + 1, itemBefore.left, itemBefore.right))
			# step 4: generate new
			for g in grammar:
				if g.right[0] == item.left:
					st.append(Item(True, item.i, item.i, 0, g.left, g.right))
		debug(st, chart, grammar)


	# step 5: check for completed item
	for item in chart[0][len(sent) - 1]:
		if not item.active:
			return True
	return False

if __name__ == "__main__":

	grammar = [ Rule("S", ["NP", "VP"]),
	 			Rule("S", ["NP", "VP", "PP"]),
				Rule("NP", ["PN"]),
				Rule("VP", ["IV"]),
				Rule("PN", ["mia"]),
				Rule("IV", ["danced"]),
			  ]
	sent = ["mia", "danced"]

	print parse(grammar, sent)
