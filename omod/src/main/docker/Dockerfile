FROM achabill/lh-toolkit

RUN mkdir -p  /root/.OpenMRS/lh-toolkit/modules

# Get radiology modules dependencies
RUN curl -L \
    "https://gitlab.com/sunbiz/lh-toolkit-docker/raw/master/modules/legacyui-1.2-SNAPSHOT.omod" \
    -o "/root/.OpenMRS/lh-toolkit/modules/legacyui-1.2-SNAPSHOT.omod"
RUN curl -L \
    "https://gitlab.com/sunbiz/lh-toolkit-docker/raw/master/modules/webservices.rest-2.17-SNAPSHOT.32bae9.omod" \
    -o "/root/.OpenMRS/lh-toolkit/modules/webservices.rest-2.17-SNAPSHOT.32bae9.omod"

COPY maven/*.omod /root/.OpenMRS/lh-toolkit/modules/
