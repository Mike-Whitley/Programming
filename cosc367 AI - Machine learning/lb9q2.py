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





prior = 0.05
likelihood = ((0.001, 0.3),(0.05,0.9),(0.7,0.99))

observation = (False, False, False)

class_posterior_true = posterior(prior, likelihood, observation)
print("P(C=False|observation) is approximately {:.5f}"
      .format(1 - class_posterior_true))
print("P(C=True |observation) is approximately {:.5f}"
      .format(class_posterior_true))

"""
I start with two lists a true list and a false list
True: []
False: []
I want to loop through obervations and likelihood at the same time
if observation is true for likelihood (False, True) is the order if its true add index 1 to true list and index 0 to false list
if the obvervation is false do the same but instead its adding 1 - value
at the very end add prior value to each list
sum both lists together
then devide true list by total and return that  

"""
