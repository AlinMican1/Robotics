# Warehouse Box Sorting Robot
Welcome to the Warehouse Box Sorting Robot project! The main class responsible for the core functionality of the robot is the Navigator_Robot class. This robot is designed to navigate a warehouse and sort boxes based on their colors by delivering each box to its designated waypoint.

# Robot Sensors
To accomplish its task, the robot is equipped with several sensors:

Infrared Sensors: These sensors detect the colors of the boxes. By utilizing infrared technology, the robot can accurately identify the color of each box and determine the appropriate destination for delivery.

Ultrasonic Sensor: The ultrasonic sensor emits a beam to measure the distance between the robot and objects in its vicinity. This allows the robot to navigate safely and avoid collisions as it moves through the warehouse.

# Robot Motors
The robot utilizes various motors to perform its actions:

Large Motors: These motors are responsible for spinning the wheels, enabling the robot to move around the warehouse efficiently. They provide the necessary propulsion for the robot's locomotion.

Medium Motor: The medium motor functions similarly to the large motors. It contains a spinning sensor inside that detects the rotation of the motor. This motor is primarily used for picking up objects during the sorting process.

# Building the Code
The build.xml file is a crucial component of the project. It is responsible for building the code for the specific version of the LeJOS system that the robot will utilize. This file ensures that the code is compiled and configured correctly, enabling seamless integration with the robot's hardware and sensors.

# Getting Started
To set up and run the Warehouse Box Sorting Robot, follow these steps:

Clone this repository to your local machine.

Ensure you have the necessary hardware components, including the robot platform and sensors.

Modify the code in the Navigator_Robot class to suit your specific robot configuration and requirements.

Use the provided build.xml file to build the code for the target LeJOS system version.

Upload the compiled code to your robot and execute it.
