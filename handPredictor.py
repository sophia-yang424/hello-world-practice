# imports 
import numpy as np
import tensorflow as tf

from keras.models import Sequential
from keras.layers import Input, Concatenate, Dense, Reshape

# counter for num of data recorded
from pokerEnv import count
if count!=0 and count%100==0:
    
    fiveCards = np.load(fiveCards)
    ratioToPot = np.load(ratioToPot)
    ratioToChip = np.load(ratioToChip)
    twoCards = np.load(twoCards)

    model = Sequential([
        input1 = tf.keras.layers.Input(shape=(1,)),
        input2 = tf.keras.layers.Input(shape=(1,)),
        input3 = tf.keras.layers.Input(shape=(1,)),
        merged = tf.keras.layers.Concatenate(axis=1)([input1, input2, input3]).
        Dense(128, input_dim=3, activation='softmax', use_bias=True)(merged),
        Dense(128, dropout=0.2, recurrent_dropout=0.2),
        Dense(),
    ])

    model.compile(loss='sparse_categorical_crossentropy', optimizer='adam', metrics=['accuracy'])

    model.fit([fiveCards, ratioToPot, ratioToChip], twoCards, epochs=100, batch_size=32)

    model.save('Directory/handPredictor.h5')
    model.save_weights('Directory/weights.h5')

