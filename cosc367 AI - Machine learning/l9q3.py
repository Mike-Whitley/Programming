import csv
from functools import reduce

def posterior(prior, likelihood, observation):
    T_list = []
    F_list = []
    for index, obs in enumerate(observation):
        #if values true add true value to true list and false value to false list
        if obs:
            T_list.append(likelihood[index][1])
            F_list.append(likelihood[index][0])
        else:
            # if values false add 1-true value  to true list and 1-false value to false list

            T_list.append((1-likelihood[index][1]))
            F_list.append((1-likelihood[index][0]))

    T_list.append(prior)
    F_list.append(1-prior)

    t = reduce(lambda x, y: x*y, T_list)
    f = reduce(lambda x, y: x * y, F_list)
    return (t/(t+f))



def learn_prior(file_name, pseudo_count=0):
    with open(file_name) as in_file:
        training_examples = [tuple(row) for row in csv.reader(in_file)]


    total = 0
    for index, element in enumerate(training_examples[1:]):
        total = total + int(element[-1])
    total = total + pseudo_count
    total_count = len(training_examples)-1
    return total/(total_count+(pseudo_count*2))



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


def nb_classify(prior, likelihood, input_vector):
    true = posterior(prior, likelihood, input_vector)

    false = 1 - true # inverse 1 minus the true value

    if true > false: # we can determine by whichever is bigger is the correct one
        return ("Spam", true)
    else:
        return ("Not Spam", false)


prior = learn_prior("spam-labelled.csv", pseudo_count=1)
likelihood = learn_likelihood("spam-labelled.csv", pseudo_count=1)

input_vectors = [
    (1,1,1,0,1,1,0,1,0,0,1,1),
    (1,1,1,1,1,1,0,0,0,1,1,1),
    (0,0,0,0,1,1,0,1,0,0,0,0),
]

predictions = [nb_classify(prior, likelihood, vector)
               for vector in input_vectors]

for label, certainty in predictions:
    print("Prediction: {}, Certainty: {:.5f}"
          .format(label, certainty))