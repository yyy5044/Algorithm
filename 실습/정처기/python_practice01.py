# ===== 1. map() + lambda =====
# 리스트의 각 요소를 제곱
nums = [1, 2, 3, 4, 5]
print(nums)
result = list(map(lambda x: x**2, nums))
print(result)

data = ['10', '20', '30']
print(data)
result2 = list(map(int, data))
print(result2)

# ===== 2. filter() + lambda =====
# 짝수만 걸러내기
nums = [1,2,3,4,5,6,7,8]
evens = list(filter(lambda x: x%2 == 0, nums))
print(evens)

words = ['hi', 'hello', 'ok', 'python']
long_words = list(filter(lambda w: len(w) >= 3, words))
print(long_words)

# ===== 3. zip() =====
# 두 리스트를 짝지어 튜플로
keys = ['a', 'b', 'c']
values = [1, 2, 3]
print(list(zip(keys, values)))

d = dict(zip(keys, values))
print(d)

# ===== 4. chr() / ord() =====
# ord: 문자 → 아스키코드
print(ord('A'))
print(ord('a'))

# chr: 아스키코드 → 문자
print(chr(65))
print(chr(97))

# 시저 암호 스타일
text = "ABC"
shifted = ''.join(chr(ord(c) + 3) for c in text)
print(shifted)

# ===== 5. range() 스텝 활용 =====
print(list(range(1, 10, 2)))
print(list(range(10, 0, -2)))
print(list(range(0, 10, 3)))

# range + sum 조합
print(sum(range(1, 11)))