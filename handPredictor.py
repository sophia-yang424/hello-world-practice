# imports  
import numpy as np
import tensorflow as tf

from keras.models import Model
from keras.layers import Input, Concatenate, Dense, Dropout

#from pokerEnv import count
def predHandGen(count):
    if count!=0 and count%100==0:
        
        '''
        fiveCards = np.load(fiveCards)
        ratioToPot = np.load(ratioToPot)
        ratioToChip = np.load(ratioToChip)
        twoCards = np.load(twoCards)
        '''

        fiveCards = np.array(fiveCards)
        ratioToPot = np.array(ratioToPot)
        ratioToChip = np.array(ratioToChip)

        twoCards = np.array(twoCards)
        twoCards = (twoCards - 1) / 51
        
        input1 = Input(shape=(5,))
        input2 = Input(shape=(1,))
        input3 = Input(shape=(1,))
        merged = Concatenate(axis=-1)([input1, input2, input3])        

        dense1 = Dense(128, activation='relu')(merged)
        dropout1 = Dropout(0.2)(dense1)
        dense2 = Dense(128, activation='relu')(dropout1)
        dropout2 = Dropout(0.2)(dense2)
        output = Dense(2, activation='sigmoid')(dropout2)

        model = Model(inputs=[input1, input2, input3], outputs=output)
        
        model.summary

        model.compile(loss='mean_squared_error', optimizer='adam', metrics=['accuracy'])

        model.fit([fiveCards, ratioToPot, ratioToChip], twoCards, epochs=100, batch_size=32)

        model.save('/Directory/handPredictor.h5')

