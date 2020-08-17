# CHANGELOG

Changelog for project EstimatedTime. This project uses semantic versioning (see [https://semver.org/](https://semver.org/))

## v. 2.0.0-alpha

  * default timer uses now System.currentTimeInMillis() as time source instead of System.nanoTime(), as the latter proved to be too unreliable for normal use cases
  * added TimeSource interface and mechanism in Timer for easier injection of custom time sources

## v. 1.0

First version