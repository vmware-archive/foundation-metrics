---
Metrics Rest endpoint
---

Rest endpoint to gather the metrics from cloud controller and Ops Metrics

- Create a cf service account user
- Assign the `cloud_controller.admin` privileges to the service account user, using the uaac cli
- Use the following manifest, and update the values accordingly

```
---
applications:
- name: metrics
  memory: 1024M
  path: target/metrics-0.0.1-SNAPSHOT.jar
  buildpack: java_buildpack_offline
  env:
    jmx.host: <jmx-endpoint>
    jmx.port: <jmx-port>
    jmx.username: <jmx-username>
    jmx.password: <jmx-password>
    cf.target: https://<api-endpoint>
    cf.username: <service-account-user>
    cf.password: <service-account-password>
    mail.protocol: smtp
    mail.host: <smtp-host>
    mail.port: <smtp-port>
    mail.username: <smtp-user>
    mail.password: <smtp-password>
    mail.starttls: true
    mail.auth: true

```

- Compile the code using `mvn clean install`
- Target to the dedicated org/space for these utils
- `cf push` to your foundation
