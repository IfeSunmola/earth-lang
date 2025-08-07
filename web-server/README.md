Building jar:
and run as
Building native:  ./mvnw -Pnative native:compile generates the native image
to the target folder, but everything to work proprly, they have to be in the
same folder as earth_jre, and earth, so you have to move it out 

Need to copy the jre and binary to the server folder