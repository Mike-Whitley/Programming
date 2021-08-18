a = [[' ', 2, 3], [1, 8, 7], [4, 6, 5]]

print(a)

flat_list = [str(item) for sublist in a for item in sublist]
flat_list.sort()


print(flat_list)