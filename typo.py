def typo_check(s1, s2):
	"""
	:param s1: Reference string
	:param s2: String to check
	:return: True, if there is zero or only one typo away
			 False, if there are two or more typo away (it can be from the same typo type, according "pale, bake ­> false")
Time Complexity: O(N)
Space Complexity: O(1)
	"""
	insert = remove = replace = i = 0

	l1 = len(s1)  # string 1 length
	l2 = len(s2)  # string 2 length

	if (l1 >= l2 + 2) or (l2 >= l1 + 2):  # two typos
		return False
	elif l1 == l2:  # same length, just check replaced chars

		if s1 in s2:  # equals
			return True

		while i < l1:
			if s1[i] == s2[i]:
				i += 1
			else:
				i += 1
				if replace < 1:  # while exist only one typo
					replace += 1
				else:
					return False  # two or more typo

	elif l1 == l2 + 1 or l2 == l1 + 1:  # at least one typo (remotion or insertion)

		while i < min(l1,l2):
			aux = s1[i] in s2[i-remove:]
			if aux and s1[i] == s2[i-remove]:
				i += 1
			else:
				i += 1
				if remove < 1:  # while exist only one typo
					remove += 1
				else:
					return False  # two or more typo

		if remove >= 1 and s1[l1-1] != s2[l2-1]:
			return False

	return True

""" - Tests """
print(typo_check("pale", "ple"))
print(typo_check("pales", "pale"))
print(typo_check("pale", "bale"))
print(typo_check("pale", "bake"))
print("=====")
print(typo_check("pizza", "pizzzza"))  # False
print(typo_check("carro", "karro"))  # True
print(typo_check("timao", "time"))  # False
print(typo_check("brazil", "razilb"))  # False
print(typo_check("amazon", "anazom"))  # False
print(typo_check("terra", "terral"))  # True
