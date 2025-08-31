# Metrics API Spring Boot Application

## Features
- Public REST API endpoint: `/hello`
- Prometheus metrics endpoint: `/actuator/prometheus` (for Grafana integration)
- Dockerfile for containerization
- Ready for Azure and AWS EKS deployment
- Kepler integration for energy/resource monitoring

## Build & Run

### Build JAR
```
mvn clean package
```

### Run Locally
```
java -jar target/metrics-api-*.jar
```

### Docker Build & Run
```
docker build -t metrics-api .
docker run -p 8080:8080 metrics-api
```

### Endpoints
- `GET /hello` — Sample REST API
- `GET /actuator/prometheus` — Prometheus metrics for Grafana

## Kubernetes (AWS EKS) Deployment
- Update `eks-deployment.yaml` with your ECR image
- Deploy with:
  ```
  kubectl apply -f eks-deployment.yaml
  kubectl apply -f eks-service.yaml
  ```
- Exposes the app via LoadBalancer on port 8080

## Kepler Integration
- Deploy Kepler DaemonSet and Service:
  ```
  kubectl apply -f kepler-daemonset.yaml
  kubectl apply -f kepler-service.yaml
  ```
- Kepler metrics exposed at `/metrics` on port 9100

## Prometheus & Grafana Setup
- Deploy Prometheus and Grafana using Helm:
  ```
  helm repo add prometheus-community https://prometheus-community.github.io/helm-charts
  helm repo update
  helm install prometheus prometheus-community/prometheus
  helm install grafana grafana/grafana
  ```
- Add scrape configs for Spring Boot and Kepler endpoints in Prometheus config
- Access Grafana UI and add Prometheus as a data source

## Example Grafana Query (Kepler)
```
sum by (pod_name) (
  irate(kepler_container_joules_total{
    container_namespace=~"$namespace",
    container_name=~"$container"
  }[$__rate_interval])
)
```

## Notes
- Ensure security groups/firewall allow access to exposed services
- For troubleshooting, use `kubectl logs <pod>`, `kubectl get svc`, and Prometheus/Grafana UIs
