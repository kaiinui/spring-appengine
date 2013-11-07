# Spring App Engine Integration

<img src="https://raw.github.com/marceloverdijk/spring-appengine/master/media/logos/logo.jpg" alt="logo" width="350" />

## Quick start

Add the library using the following Maven dependency:

    <dependency>
        <groupId>com.googlecode.spring-appengine</groupId>
        <artifactId>spring-appengine</artifactId>
        <version>0.2</version>
    </dependency>

Or when using Gradle:

    compile "com.googlecode.spring-appengine:spring-appengine:0.2"    

Next, read the latest javadoc api [here](http://marceloverdijk.github.io/spring-appengine/docs/0.1/javadoc-api).

## Release history

* 0.2 (WIP)
    * Added Objectify integration (WIP)
    * Added convenient Spring factory beans for creating App Engine service instances
    * Added convenient Google App Engine JSP taglib encapsulating App Engine specific functionality like printing the application identifier, login link, user details etc.
    * Added reference documentation (based on AsciiDoc) (WIP)
    * Upgraded to Google App Engine 1.8.7
    * Upgraded to Spring Framework 3.2.5
* 0.1 (2013-10-17)
    * Initial release containing Spring @Cacheable support using App Engine Memcache

## Roadmap

* 0.X
    * Spring Security integration
    * Task Queue template similar to Spring JmsTemplate
    * Thymeleaf dialect equivalent to the Google App Engine JSP taglib

## Community

* Have a question that's not a feature request or bug report? [Ask on the mailing list.](http://groups.google.com/group/spring-appengine)

## Author

**Marcel Overdijk**

+ [http://twitter.com/marceloverdijk](http://twitter.com/marceloverdijk)
+ [http://github.com/marceloverdijk](http://github.com/marceloverdijk)

Logo designed and contributed by [Ruud Voost](http://www.ruudvoost.com).

## Copyright and License

Copyright 2013 Marcel Overdijk

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
