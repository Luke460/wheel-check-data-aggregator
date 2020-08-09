# Wheel Check Data Aggregator

***Wheel check data aggregator*** is a useful tool that helps you to improve [***Wheel check***](https://www.racedepartment.com/downloads/lut-generator-for-ac.9740/)'s log files by aggregating data to emphasizing the behavior of your steering wheel and eliminating reading errors.

...but what does it mean? what is data aggregation?

## Data Aggregation example (with Logitech G29)

Data aggregation is a widely used process that helps to extract the behavior of a set of raw data by mitigating reading errors. It's used mostly in advanced security and control systems (sensors networks, alarms). 

In the following example you can see an example of a generated log file for a ***Logitech G29***. The red line represents the raw data collected by the standard [***Wheel check***](https://www.racedepartment.com/downloads/lut-generator-for-ac.9740/) calibration procedure, while the blue line is the improved output file generated by ***Wheel Check Data Aggregator***.

![example](images/G29-GRAPH.png)

With this utility, you can choose the degree of data aggregation (named aggregation order) by editing the ***config.json*** file:
```
{
	"input_file":"example.csv",
	"aggregation_order":5
}
```

To run the utility, just put your [***Wheel check***](https://www.racedepartment.com/downloads/lut-generator-for-ac.9740/)'s log file (example.csv) in the program folder and execute ***WheelCheckDataAggregator.jar***:

![icon](images/icon.png)

If everything goes well, you should get the following output message:

![success](images/success.png)

You can use generated log files as [***Lut Generator***](https://www.racedepartment.com/downloads/lut-generator-for-ac.9740/) input to improve generated lut files:

![comparison](images/Comparison.png)

For more information, read the ***README*** file inside the program folder.

## DOWNLOADS

 + [***Wheel Check Data Aggregator***](https://github.com/Luke460/wheel-check-data-aggregator/releases/tag/0.0.1)
