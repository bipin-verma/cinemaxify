# Changelog

All notable changes to this project are documented in this file.

## [v2.0.0] - 2026-03-18

### Summary
Second version of the Cinemaxify project that adds plan selection and a repeated enrollment workflow on top of the original member-profile flow.

### Highlights
- Added `Plan`, `NormalPlan`, and `PremiumPlan` to model membership plan choices.
- Switched Spring XML wiring from basic member beans to member-plan combinations.
- Updated the console workflow to let users choose self or spouse, then normal or premium.
- Added the option to enroll another person in the same runtime session.
- Expanded tests and documentation to cover the new flow.

### Notes
This version upgrades the project from simple member-detail collection into a more complete membership-enrollment console flow.

## [v1.0.0] - 2026-03-18

### Summary
Initial version of the Cinemaxify project built as a Java 17 console application with Spring XML bean configuration.

### Highlights
- Added self and spouse membership profiles through Spring-managed beans.
- Added a console workflow to capture member name, age, contact number, and address.
- Added input validation for menu choice and numeric fields.
- Added tests for bean loading, member output, and workflow behavior.
- Added GitHub-ready documentation and changelog files.

### Notes
This version establishes the project as a simple membership-details demo suitable for GitHub and portfolio presentation.
