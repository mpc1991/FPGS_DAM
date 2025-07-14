# component2image
#### Macià Porcel Cifre
#### DI03

### How to use it?
#### The component name inside the .jar is "jPanel2Images".

#### To configure the listener in your jFrame use this code:
###### jPanel2Images.addListener(new MyEventListeners(){
######            @Override
######            public void onBlobDifference(String msg) {
######                jTextArea.setText(msg); // For comodity I recomend the use of a jTextArea but change it as your need.
######            }
######        });

#### On your start button use this code:
###### jPanel2Images.setConnectionString(connectionString); Use the connection string of your storage
###### jPanel2Images.setContainerName(containerName); Use your container name
###### jPanel2Images.setRequestInterval(requestInterval); Set the request interval as int value
###### jPanel2Images.setIsPolling(true); This is equivalent to "jPanel2ImagesTimer.start();" but has some extra features like changing colors of the component

#### On your stop button use this code:
###### jPanel2Images.setIsPolling(false); This is equivalent to "jPanel2ImagesTimer.stop();" but has some extra features like changing colors of the component
