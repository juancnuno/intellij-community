print((1, 2, *(3, 4), 5))
print([1, 2, *[3, 4]])
print([*(1, 2), 3])
xs1 = 1, *f(), 3, 4
xs2 = [1, 2, *f(), *f(), 5]
xs3 = 1, 2, *(3, 4)
print({**{1: 2}, 3: 4})
print({1: 2, **{3: 4}, 5: 6})
print({**f()})
print({**f(), 3: 4})
print({1: 2, **f()})
print({**f(), **f()})
print({1, 2, *(3, 4)})
print({*(1, 2), 3})
print({*(1, 2)})
