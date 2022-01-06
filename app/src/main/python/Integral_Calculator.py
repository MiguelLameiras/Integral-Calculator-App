from math import *

def Integral(expression,a,b):
    #ESTA FUNÇÃO NÃO É NECESSARIA É SO PORQUE A IDEIA ORIGINAL ERA OUTRA
    integral = Integral_Simpson(expression,a,b)
    formatted_string = "{:.7f}".format(integral)
    return formatted_string

#Cálculo do Integral Através do método de Simpson
def Integral_Simpson(expression,a,b):
    a = float(a)
    b = float(b)
    total = 0
    n = 10000 #Numero de partições, tem de ser par
    h = (b-a)/n #step_size

    #ALGORITMO DE SIMPSON
    for i in range(n):
        x = a + i*(h)
        if (i % 2) == 0:
            #O eval() pode trazer vários problemas de segurança mas como não se importou os.system() não deve ser muito grave
            total = total + eval(expression)
            x = x + h
            total = total + 3*eval(expression)
        else:
            total = total + eval(expression)
            x = x + h
            total = total + eval(expression)
    result = (h/3)*(total)

    return result