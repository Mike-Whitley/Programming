import csv


def learn_likelihood(file_name, pseudo_count=0):
    with open(file_name) as in_file:
        training_examples = [tuple(row) for row in csv.reader(in_file)] #open the csv file as read in all the values

    true_values = 0
    row_count = 0
    likelihoods = []


    for row in training_examples:
        is_spam = 1 if row[-1] == '1' else 0
        if row_count == 0:
            #if its the first row we want the liklihoods to be the psuedo count value pairs for length of all items
            likelihoods = [[pseudo_count, pseudo_count] for i in range(len(row) - 1)]
        else:
            for i in range(len(row) - 1):
                likelihoods[i][is_spam] += int(row[i])

        row_count += 1
        true_values += is_spam

    #determine if probability likligood is true of false divide and add together
    for probability in likelihoods:
        probability[True] /= (true_values + 2 * pseudo_count)
        probability[False] /= (row_count - 1 - true_values + 2 * pseudo_count)

    return likelihoods


likelihood = learn_likelihood("spam-labelled.csv")

print("P(X1=True | Spam=False) = {:.5f}".format(likelihood[0][False]))
print("P(X1=False| Spam=False) = {:.5f}".format(1 - likelihood[0][False]))
print("P(X1=True | Spam=True ) = {:.5f}".format(likelihood[0][True]))
print("P(X1=False| Spam=True ) = {:.5f}".format(1 - likelihood[0][True]))