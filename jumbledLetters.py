def jumbled(s1, s2):
	"""
	:param s1: Reference string
	:param s2: String to check
	:return: True, if possible to read
			 False, if not possible to read

Time Complexity: O(N)
Space Complexity: O(1)


	"""

	if s1[0] != s2[0]:
		return False
	elif (len(s1) == 2) and (s1[1] != s2[1]):
		return False
	else:
		diff = 0
		for i in range(1, len(s1)):
			if s1[i] != s2[i]:
				diff += 1
			if diff > len(s1)*(2/3):
				return False

		return True

""" - Tests """
print(jumbled("you", "yuo"))
print(jumbled("probably", "porbalby"))
print(jumbled("despite", "desptie"))
print(jumbled("moon", "nmoo"))
print(jumbled("misspellings", "mpeissngslli"))
print("====")
print(jumbled("can", "cna"))
print(jumbled("read", "raed"))
print(jumbled("this", "tihs"))
print(jumbled("easily", "eylasi"))
print(jumbled("the", "teh"))

