# LibreHealth Radiology

[![Build Status](https://secure.travis-ci.org/openmrs/openmrs-module-radiology.png?branch=master)](https://travis-ci.org/openmrs/openmrs-module-radiology) [![Coverage Status](https://coveralls.io/repos/openmrs/openmrs-module-radiology/badge.svg?branch=master&service=github)](https://coveralls.io/github/openmrs/openmrs-module-radiology?branch=master) [![Codacy Badge](https://api.codacy.com/project/badge/grade/5e0137f0c916494eaa3ba7de43149ef7)](https://www.codacy.com/app/teleivo/openmrs-module-radiology_2) [![Dependency Status](https://www.versioneye.com/user/projects/57a194fb3d8eb6002f560778/badge.svg?style=flat)](https://www.versioneye.com/user/projects/57a194fb3d8eb6002f560778)

#### Table of Contents

1. [Overview](#overview)
2. [Build](#build)
3. [Install](#install)
  * [Docker](#docker-whale)
  * [Demo data](#demo-data)
4. [Documentation](#documentation)
  * [Website](#website)
  * [Developer guides](#developer-guides)
  * [Wiki](#wiki)
5. [Contributing](#contributing)
  * [Code](#code)
  * [Translation](#translation)
6. [Issues](#issues)
7. [Limitations](#limitations)
8. [Community](#community)
9. [Support](#support)
10. [License](#license)

## Overview

LibreHealth radiology is a module adding capabilities of a Radiology
Information System (RIS) onto LibreHealth Toolkit.

## Build

### Prerequisites

You need to have installed

* a Java JDK 8
* the build tool [Maven](https://maven.apache.org/)

You need to configure Maven to use the JAVA JDK 8

```bash
mvn -version
```

Should tell you what version Maven is using.

You need to clone this repository:

```bash
git clone https://gitlab.com/librehealth/librehealth-radiology.git
```

### Command

After you have taken care of the [Prerequisites](#prerequisites)

Execute the following command:

```bash
cd librehealth-radiology
mvn clean package
```

This will generate the radiology module in `omod/target/radiology-{VERSION}.omod` which you will have to deploy into LibreHealth Toolkit.

## Install

The easiest way to install the module is to use [Docker](https://www.docker.com/).

### Docker :whale:

This module can be baked into a Docker image so you can easily run and test it.

#### Prerequisites

After you have taken care of the [Build Prerequisites](#prerequisites)

Make sure you have [Docker](https://docs.docker.com/) installed.

#### Build

Build the Radiology Module and its Docker image:

```bash
cd librehealth-radiology
mvn clean package docker:build
```

#### Run

To run an instance of the LibreHealth Radiology Module execute (assumes you have
created a Docker image):

```bash
cd librehealth-radiology
mvn docker:start
```

LibreHealth Toolkit will be accessible at `http://<IP ADDRESS>:8080/lh-toolkit`

**NOTE: The IP address varies depending on your setup.**

If you are using [Docker machine](https://docs.docker.com/machine/) refer to its documentation on how to get the IP address.
If you are on Linux it will probably be will be `localhost`.

#### Documentation

Please read the corresponding [DOCKER.md](docs/DOCKER.md) for more detailed
explanations on using Docker with the Radiology Module.

### Demo data

You can import the demo data set [demo-data.sql](acceptanceTest/resources/demo-data.sql) into
your database which enables you to try out the modules features or test your
changes.

Please read the corresponding [DEMO-DATA.md](docs/DEMO-DATA.md).

## Documentation

### Website

For a detailed guide on ways to install and configure this module see

http://teleivo.github.io/docs-openmrs-module-radiology/

### Developer guides

Please check out the readme files at [docs](docs/).

### Wiki

For some more background informations on the module see

https://gitlab.com/librehealth/librehealth-radiology/wikis/home

## Contributing

Contributions are very welcome, we can definitely use your help!

### Code

Check out our [contributing guidelines](CONTRIBUTING.md), read through the [Developer guides](#developer-guides).

After you've read up :eyeglasses: [grab an issue](https://gitlab.com/librehealth/librehealth-radiology/issues).

### Translation

We use

https://www.transifex.com/openmrs/OpenMRS/radiology-module/

to manage our translations.

The `messages.properties` file in this repository is our single source of
truth. It contains key, value pairs for the English language which is the
default.

Transifex fetches updates to this file every night which can then be translated
by you and me on transifex website itself. At any time we can pull new translations from transifex
back into this repository. Other languages like for ex. Spanish will then be in
the `messages_es.properties` file.

If you would like to know more about transifex from the coding side read

https://wiki.openmrs.org/display/docs/Maintaining+OpenMRS+Module+Translations+via+Transifex

## Issues

To file new issues or help to fix existing ones please check out

https://gitlab.com/librehealth/librehealth-radiology/issues

## Limitations

This module is not yet officially released to the [LibreHealth Toolkit](https://toolkit.librehealth.io/).

The API and UI are not yet stable and subject to frequent changes.

The module depends on [LibreHealth Toolkit v 2.0.1](https://gitlab.com/librehealth/lh-toolkit) so it cannot run on any version lower than that.

The module currently depends on [LibreHealth Legacy UI](https://gitlab.com/sunbiz/lh-toolkit-legacyui)
which provides the UI but it is planned to extract the UI into a separate module so this module only provides the Java and
REST API without forcing a specific UI onto anyone.

## Community

[![LibreHealth Forums](https://img.shields.io/badge/librehealth-forum-orange.svg)](https://forums.librehealth.io/)
[![LibreHealth Chat](https://img.shields.io/badge/librehealth-chat-orange.svg)](https://chat.librehealth.io)
[![LibreHealth Radiology Telegram](https://img.shields.io/badge/librehealth%20radiology-telegram-blue.svg)](http://telegram.me/LibreHealth%20Radiology)
[![LibreHealth Radiology Wiki](https://img.shields.io/badge/librehealth%20radiology-wiki-blue.svg)](https://gitlab.com/librehealth/librehealth-radiology/wikis/home)

## Support

Ask questions on [LibreHealth Forums](https://forums.librehealth.io/).

## License

[MPL 2.0 w/ HD](http://openmrs.org/license/) © [LibreHealth](http://librehealth.io/)
