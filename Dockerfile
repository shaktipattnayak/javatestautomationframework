FROM markhobson/maven-chrome

# Root user enables file creation and package installations in RUN instructions
USER      root

# Get Chrome and Chromedriver running versions in image
RUN       google-chrome --version
RUN       chromedriver --version

# Copy everything except hidden files (i.e. .git)
COPY      ./ /tafu_repo

WORKDIR  /tafu_repo/

RUN     mvn clean install