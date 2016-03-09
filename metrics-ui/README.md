---
Metrics UI
---

Frontend application to display the PCF foundation metrics

- Edit the `/js/metrics.js` file and update the variable `endpoint` to point to the metrics rest endpoint
- Use the following manifest to push the application to cloud foundry
```
---
applications:
- name: metrics-ui
  memory: 1024M
  path: ../metrics-ui/
  buildpack: staticfile_buildpack
```
- Target to the dedicated org/space for these utils
- `cf push` to your foundation

## Overall Dashboard
![DashBoard](../images/dashboard.png)

## Ops Metrics Details and OpenTSDB details for jobs
![Job Metrics](../images/job_metrics.png)

## You can view the health of the jobs
![Job Health](../images/job_health.png)

## View All Orgs
![Org Details](../images/orgs.png)

## View All Spaces
![Space Details](../images/spaces.png)

## View All Applications
![Application Details](../images/applications.png)

## Configure your email settings
![Email Settings](../images/mail_settings.png)
