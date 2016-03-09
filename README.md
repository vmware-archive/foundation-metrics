---
PCF Foundation metrics
---

This application has 2 services:
- Metrics Rest service
- Metrics UI

The Metrics Rest service is a java application, that consumes the data from the JMX endpoint and the cloud controller api endpoint.

The Metrics UI is a static code, and it consumes metrics from the rest service.

Please refer to the [Metrics Rest Service README.md](/metrics/README.md) and [Metrics UI README.md](/metrics-ui/README.md) for further instructions

## Overall Dashboard
![DashBoard](./images/dashboard.png)

## Ops Metrics Details and OpenTSDB details for jobs
![Job Metrics](./images/job_metrics.png)

## You can view the health of the jobs
![Job Health](./images/job_health.png)

## View All Orgs
![Org Details](./images/orgs.png)

## View All Spaces
![Space Details](./images/spaces.png)

## View All Applications
![Application Details](./images/applications.png)

## Configure your email settings
![Email Settings](./images/mail_settings.png)
