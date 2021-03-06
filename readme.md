## Download

+ [***Steering Wheel Master***](https://github.com/Luke460/steering-wheel-master/releases) ***(latest version)***

## Donate

Donations are not required, but always accepted with pleasure. Thanks for your support!
 - [***Go to the donation page***](https://www.paypal.com/donate?hosted_button_id=WVSY5VX8TA4ZE)

---

# Steering Wheel Master

[***Steering Wheel Master***](https://github.com/Luke460/steering-wheel-master/releases) is a useful tool that helps to improve the behavior of your steering wheel by aggregating data and eliminating reading errors, dead zones and clipping problems.

This application is free and open source. The development was born to help the simracers community get the most out of their steering wheels, regardless of the price range of the hardware.

This procedure uses as input a ***force feedback calibration file***, and by processing it, is able to produce a customizable lookup table (or ***lut file***).

...but what does it mean? what is data aggregation?

## Data Aggregation example

Data aggregation is a widely used process that helps to extract the behavior of a set of raw data by mitigating reading errors. It's used mostly in advanced security and control systems (sensors networks, alarms). 

In the following graph you can see an example of a generated log file for a ***Logitech G29***. The red line represents the raw data, while the blue line is the dataset generated by ***Steering Wheel Master*** used in this example for the generation of a lookup table (or ***lut file***).

![example](images/G29-GRAPH.png)

## Before to start

In these procedures there is always a remote possibility of damaging your hardware. Only proceed if you are aware of the risk. I take no responsibility for any damage caused by this procedure.

Before to start, you have to create a ***force feedback calibration file*** for your own steering wheel. Depending on the program you intend to use, you need to properly set the *force column index* and the *delta column index* in the ***CSV settings*** menu.

In this example my input csv file was generated by using ***iRacing force feedback test*** (v1.72), a tool made by David Tucker of iRacing.com that measures the change in the steering wheel position for each input applied.
The used settings are ***Step Log 2 (linear force test)*** with ***Max Count*** set to ***100***.

## How to use

To run ***Steering Wheel Master***, download the [***latest version***](https://github.com/Luke460/steering-wheel-master/releases), unzip the file and run ***SteeringWheelMaster.jar*** (requires Java 7 or later):

[![icon](images/Icon.png)](https://github.com/Luke460/steering-wheel-master/releases)

Now you have to locate your ***force feedback calibration file*** and configure ***Steering Wheel Master***.


First of all, you have to choose between **advanced lut generation** and **linear lut generation**:
 - **advanced lut generation** uses all the data provided by the csv file to attempt to correct the steering wheel reactions. 
 - **linear lut generation** uses the csv file only to correct the dead zone in order to not to alter the standard behavior of your steering wheel.
 
Since ***geared wheels*** tend to have clipping issues that can invalidate the ***force feedback calibration file***, I highly recommend to choose ***linear lut generation*** for this kind of wheel.
   
   | **Steering wheel type** | ***Example*** | **Lut generation method** | **Aggregation order** | **FFB peak reduction** | **FFB power enhancement** | **dead zone enhancement** (depends on your wheel dead zone) |
   |-------------------------|---------------------------|----|----|----|----|----|
   | ***belt wheels***       | Thrustmaster T300, T500 or Fanatec CSL elite | advanced | ~ 5 | 0 | 0 | ~ 0 |
   |                         |                                              | linear | - | 0 to 5 | 0 to 5 | ~ 5 |
   | ***mixed wheels***      | Thrustmaster TXM or T150  | advanced | ~ 5 | 0 | 0 | ~ 0 |
   |                         |                           | linear | - | 0 to 5 | 0 to 5 | ~ 5 |
   | ***geared wheels***     | Logitech G25, G27, G29, G923 and related XBOX versions | advanced | ~ 5 | 0 | 0 | ~ 0 |
   |                         |        *(recommended for Logitech wheels)*             | linear | - | 0 to 5 | 0 to 5 | ~ 5 |

**NOTE:** 
 - Keep in mind that every steering wheel is different, you may need to find the perfect settings for your own device!
 - Some ***linear lut generation*** presets are available in the dedicated section below.
 - The in-game **FFB gain** must be set to 100% and the **minimum FFB** must be set to 0%.
 - If you are using a high level steering wheel, you probably will not get any benefit by using this application, as its behavior will already be linear, silent and without any dead zone.

 **IN GENERAL:** 
 - Lower values of ***aggregation order*** makes your force feedback correction more precise, while higher values makes your force feedback smoother.
   - Don't go too high, or you will lose precision in force feedback correction.
   - Don't go too low or you will not benefit of the aggregation procedure.
 - You can click on ***auto*** to (hopefully) calculate a good *aggregation order* for your steering wheel.
 - You can click on ***CSV settings*** to configure how to read the input csv file.
 - You can increase ***FFB peak reduction*** if you have clipping issues with your steering wheel or simply you want to reduce the effects of very rough curbs, bumps, strong vibrations, etc. Enabling this option may also slightly reduce the overall noise of your steering wheel and extend its lifespan.
 - You can use ***FFB power enhancement*** to boost your wheel FFB strength. It's mostly intended for steering wheel with low engine power. This option increases the low and medium values of FFB in a progressive manner.
 - You can increase ***dead zone enhancement*** if you have vibrations in the central area of the steering wheel.
 - You can decrease ***dead zone enhancement*** if you still have a FFB deadzone with the generated lut.
 - You can select ***linearize near zero*** to change the aggregation strategy for low values of the force feedback. It can help remove vibrations in the center of some steering wheels. Activating this option requires you to also increase the ***deadzone enhancement*** value.
 - Experiment and play with the ***preview*** button!
 
![menu](images/menu.png)

**NOTE:** These settings are ***not recommended*** for a ***Logitech G29***, use ***linear lut generation*** for this steering wheel.

Now click on ***Generate lut*** and if everything goes well, you should get the following output message:

![success](images/success.png)

**DONE:** The generated file is located in the *Steering Wheel Master* folder.

## Linear lut generation presets

The following settings can be applied to every steering wheel to generate a linear lut. The in-game **FFB gain** must be set to 100% and the **minimum FFB** must be set to 0%.

### Silent mode
This preset is noticeably quieter, although the force feedback is slightly weaker. Since the peak power is lower, this option preserves the lifespan of the device.
 - ***Lut generation method***: linear lut generation
 - ***FFB Peak reduction***: 5 (this option slightly reduce the overall noise of your steering wheel and extend its lifespan)
 - ***FFB power enhancement***: 0
 - ***Dead zone enhancement***: 5 (this value depends on your wheel dead zone, but should be similar to this value)
 
### Standard mode
This preset offers a good compromise between strength and quietness. Basically it only removes the dead zone leaving a default behavior.
 - ***Lut generation method***: linear lut generation
 - ***FFB Peak reduction***: 0
 - ***FFB power enhancement***: 0
 - ***Dead zone enhancement***: 5 (this value depends on your wheel dead zone, but should be similar to this value)

### Competitive mode
This preset makes force feedback noticeably stronger (but makes the steering wheel slightly noisy) by increasing the low and medium values of FFB in a progressive manner. Keep in mind that the maximum peak force is always limited to 100%.
 - ***Lut generation method***: linear lut generation
 - ***FFB Peak reduction***: 0
 - ***FFB power enhancement***: 5
 - ***Dead zone enhancement***: 5 (this value depends on your wheel dead zone, but should be similar to this value)

## How to use LUT files in *Assetto Corsa* and *Assetto Corsa Competizione*

To allow *AC* and *ACC* to use your lut file, you need to create a text file named *ff_post_process.ini*

Here is an example:
```
[HEADER]
VERSION=1
TYPE=LUT
ENABLED=1

[GAMMA]
VALUE=1

[LUT]
CURVE=AG0-PR5-DZ5-LL.lut
```
**NOTE:** You need to set the ***CURVE*** variable with the name of ***your*** LUT file.

Now just place both the .lut file and the .ini file in the following folder:
 - For AC: ```C:\Users\<user_name>\Documents\Assetto Corsa\cfg```
 - For ACC: ```C:\Users\<user_name>\Documents\Assetto Corsa Competizione\Config```

## How to use LUT files in *Assetto Corsa* with Content Manager

For Assetto Corsa, if you are using ***Content Manager*** as game launcher, you need to use its interface to set up your lut file as follows:

![1](images/ac-settings.png)
![2](images/left-menu.png)
![3](images/ffb-settings.png)

---
 
## New Features

 - [v1.5](https://github.com/Luke460/steering-wheel-master/releases): Multiple Spike detection and correction:

![spike-detection](images/update-1.5.png)

- [v1.7](https://github.com/Luke460/steering-wheel-master/releases): Error correction improvement:

![error-correction](images/update-1.7.png)

- [v2.0](https://github.com/Luke460/steering-wheel-master/releases): Added user interface and output graph comparison:

![user-interface](images/update-2.0.png)

- [v2.2](https://github.com/Luke460/steering-wheel-master/releases): Added lut generation feature.

- [v2.3](https://github.com/Luke460/steering-wheel-master/releases): Added 'Auto' button to suggest an aggregation order based on the given csv file.

- [v2.4](https://github.com/Luke460/steering-wheel-master/releases): Higher lut file output resolution.

- [v2.5](https://github.com/Luke460/steering-wheel-master/releases): Added 'Dead zone enhancement' option (not needed on most steering wheels).

- [v2.6](https://github.com/Luke460/steering-wheel-master/releases): Improved 'Dead zone enhancement': slider added.

- [v2.8](https://github.com/Luke460/steering-wheel-master/releases): Added option to perform dead zone correction only.

- [v2.9](https://github.com/Luke460/steering-wheel-master/releases): Added 'FFB peak reduction' feature.

- [v2.10](https://github.com/Luke460/steering-wheel-master/releases): Added 'Linearize near zero' feature.

- [v2.11](https://github.com/Luke460/steering-wheel-master/releases): Added 'FFB power enhancement' feature.

- [v2.12](https://github.com/Luke460/steering-wheel-master/releases): Added tooltips in the user interface.

- [v2.13](https://github.com/Luke460/steering-wheel-master/releases): Improved input csv files compatibility.

- [v3.0](https://github.com/Luke460/steering-wheel-master/releases): A new name: Steering Wheel Master!
