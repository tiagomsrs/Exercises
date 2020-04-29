def replacing(Sin, tam = 0):
    """
    :param Sin: Input string to replace spaces with '&32'
    :param tam: Real string length, by default = 0 (not used in this function)
    :return: String replaced
Time Complexity: O(1)
Space Complexity: O(1)
    """
    Sout = Sin.strip()
    return Sout.replace(" ", "&32")

""" - Tests """
Sin = "User is not allowed      "
tam = 19
print(replacing(Sin, tam))
