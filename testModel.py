import numpy as np

from keras.models import load_model

model = load_model('handPredictor.h5', compile=False)
model.compile(loss='sigmoid', optimizer='adam', metrics=['accuracy'])

test1 = [[11,8,42,35,51]]
test2 = [0.373]
test3 = [43]
test1 = np.array(test1)
test2 = np.array(test2)
test3 = np.array(test3)
test = [test1, test2, test3]

result = model.predict(test)

predictedHand = round(result * 51)
print(predictedHand)

