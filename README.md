---
PCF Foundation metrics
---

This application has 2 services:
- Metrics Rest service
- Metrics UI

The Metrics Rest service is a java application, that consumes the data from the JMX endpoint and the cloud controller api endpoint.

The Metrics UI is a static code, and it consumes metrics from the rest service.

Please refer to the [Metrics Rest Service README.md](/metrics/README.md) and [Metrics UI README.md](/metrics-ui/README.md) for further instructions
