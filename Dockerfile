# Dockerfile to build container for unit testing.
#
# To build the image, run the following from this directory:
#   docker build -t beast_testing .
#
# To run the tests, use
#   docker run beast_testing
#
# To run the tests interactively, use
#   docker run -it -p 5900:5900 beast_testing /bin/bash
# This will give you a shell in the container. From this
# shell, run
#   vncserver $DISPLAY -geometry 1920x1080; ant travis
#
# The previous command exposes the VNC session, so while the
# BEAUti test suite is running you can run a VNC viewer and
# connect it to localhost (password: password) to observe
# the graphical output of these tests.

FROM openjdk:8

# Install Apache Ant
RUN apt-get update && apt-get install -y ant

# Install and configure VNC server
RUN apt-get update && apt-get install -y tightvncserver twm
ENV DISPLAY :0
ENV USER root
RUN mkdir /root/.vnc
RUN echo password | vncpasswd -f > /root/.vnc/passwd
RUN chmod 600 /root/.vnc/passwd

# Install BEAGLE
RUN apt-get update && apt-get install -y build-essential autoconf automake libtool pkg-config
RUN cd /root && git clone --depth=1 https://github.com/beagle-dev/beagle-lib.git
RUN cd /root/beagle-lib && ./autogen.sh && ./configure --prefix=/usr/local && make install
RUN ldconfig

# obtain libraries
RUN cd /root && git clone --depth=1 https://github.com/Compevol/beast2.git
RUN cd /root && git clone --depth=1 https://github.com/beast-dev/beast-mcmc.git

# Ant build fails if the repo dir isn't named xbeast
RUN mkdir /root/xbeast
WORKDIR /root/xbeast

ADD . ./

CMD vncserver $DISPLAY -geometry 1920x1080; ant travis
