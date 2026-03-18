# Changelog

All notable changes to this project are documented in this file.

## [v3.0.0] - 2026-03-18

### Summary
Third version of the Cinemaxify project that keeps the plan-based enrollment flow from `v2` and changes the Spring bean wiring to setter-based dependency injection.

### Highlights
- Reworked `Self` and `Spouse` to receive `Plan` through a setter instead of a constructor.
- Updated `MemberProfile` to support property-based plan injection.
- Changed Spring XML bean definitions from constructor args to `<property>` injection.
- Kept the `v2` console workflow and validation behavior intact.
- Updated tests and documentation to reflect the new dependency injection style.

### Notes
This version is mainly an architectural refinement that demonstrates another common Spring wiring style without changing the user-facing enrollment flow.

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
