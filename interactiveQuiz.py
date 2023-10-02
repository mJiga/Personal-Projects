score = 0

def scoring(answer):
    global score
    if answer == 'a':
        score += 3
    elif answer == 'b':
        score += 2
    else:
        score += 1

def finalScore():
    global score
    if score >= 11:
        print('You do like pizza!')
    elif score <= 11 and score > 5:
        print('You like pizza a little.')
    else:
        print('You do not like pizza at all...')
    print('Score: ' + str(score))

questions = [
            '''Do you like pizza?
               a. Yes, of course   b. I guess   c. No ... ''',
            '''How many slices you eat every time you have pizza?
               a. 3 or more   b. 2 or 1  c. I do not eat any ... ''',
            '''When eating out, would you rather go to a pizza place?
               a. Yes   b. Maybe   c. No ... ''',
            '''Do you prefer sweet or salty food?
               a. Salty   b. Sweet   c. I have no preference ... ''',
            '''Would you place pizza at your top 5 favourite meals?
               a. Yes   b. Maybe   c. No ... '''
]

print('Hi, Welcome to my interactive quiz! The objective is to see how much you like pizza!\n')
print('Starting...\n')

for question in questions:
    while True:
        answer = input(question)
        if answer in ('a', 'b', 'c'):
            scoring(answer)
            break 

finalScore()