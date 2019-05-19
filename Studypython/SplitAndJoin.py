a,b,c,d,e = 'Hello/Nice/To/Meet/You'.split('/')

print(a,b,c,d,e)

coffee = ['top', 'kanu', 'cantata', 'maxim']
c_brand = '-coffee '.join(coffee)
print(c_brand)

name =['kim','lee','park','jung']

result =[]
for i in name:
    if i =='kim':
        result.append(i+'= a')
    #else:
        #result.append(i+'= b')
print(result)

a = '= a'
grade = [i+a for i in name if i =='kim']
print(grade)

multi = [2,3]
num = [1,2,3,4,5,6]

gugudan = [i*j for i in multi for j in num]
print(gugudan)
gugudan = [[i*j for i in multi] for j in num]
print(gugudan)
gugudan = [[i*j for i in num] for j in multi]
print(gugudan)

man = ['철수', '성운', '민영']
woman = ['은미', '민영', '수진']

make_couple = [i+", "+j for i in man for j in woman if not(i==j)]
print(make_couple)

for i,v in enumerate('nice to see you'.split()):
    print(i, v)

make_couple = list(enumerate(make_couple))
print(make_couple)

greeting ={i:v for i, v in enumerate('good to see you'.split())}
print(greeting)

vector_a =[1,4,8,9]
vector_b =[2,3,5,6]
vector_c = zip(vector_a, vector_b)
print(list(vector_c))

for i,j in zip(vector_a, vector_b):
    print(i, j)

sum_vector_ab = [sum(i) for i in zip(vector_a, vector_b)]
print(sum_vector_ab)

match_couple = {i : (m,w) for i, (m,w) in enumerate(zip(man,woman))}
print(match_couple)

mul = lambda v1,v2 : {i : (a,b) for i, (a,b) in enumerate(zip(v1,v2))}
print(mul([1,2],[3,4]))
