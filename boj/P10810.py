N, M = map(int, input().split())

lst = [0 for _ in range(N + 1)]
for _ in range(M):
    i, j, k = map(int, input().split())

    for idx in range(i, j + 1):
        lst[idx] = k

for l in lst[1:]:
    print(l, end=" ")
