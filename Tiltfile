docker_build('catalog-service', '.')
docker_build('polar-postgres-local', './postgres-custom')
k8s_yaml([
    'k8s/deployment.yml',
    'k8s/service.yml',
    'C:/Users/victo/Documents/IdeaProjects/polar-deployment/kubernetes/plataform/development/services/postgresql.yml'
])
k8s_resource('catalog-service', port_forwards=['9001'])
k8s_resource('polar-postgres', port_forwards=['5432'])