![SPAT](spat.jpg) Configuration
=====


The **Configuration** class manages global configurations for
*jsqltoxml*. By default the *conf.properties* file is loaded; i.e
This file should only contain global configuration such as language, output
or engine settings. The default language is *'en'* & the default Engine
output format is XML. The language specific file is loaded using the prefix
specified in the *conf.properties* file. e.g. [en].properties, where
*en* is the prefix.




###Constructors
- [Configuration()](#951897175)

###Methods
- [i18n(String propertyName, Object... args)](#-3678878)  returns String
- [i18n(String propertyName)](#1872051524)  returns String


<a name="951897175">Configuration</a>()
-----


Instantiates a new configuration.
At creation time the `core.properties` & `en.properties` (language specific property) file
is loaded



#### <span style="font-size:12px;color:#AAAAAA">String</span> <a style="font-size:16px;" name="-3678878">i18n</a><span style="font-size:16px;">(String propertyName, Object... args)</span>
- <b>propertyName</b>: the property name
- <b>args</b>: 
        - string arguments list

- <b>returns</b>: formatted i18n string

    Retrieves i18n value from a property file. If the returned property
    value supports formatting, then the second param will be used to
    return formatted string.



#### <span style="font-size:12px;color:#AAAAAA">String</span> <a style="font-size:16px;" name="1872051524">i18n</a><span style="font-size:16px;">(String propertyName)</span>
- <b>propertyName</b>: 
        the property name

- <b>returns</b>: property value

Retrieves i18n value from a property file.
Property names prefixed with `c_` are core properties and will be search
for in the `core.properties`. if the property does not contain the prefix
the `en.properties` file is searched. The language prefix `en`, on
language specified in the configuration file.
Support can be added for any language.



