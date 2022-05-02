mavenCentral()
google()
maven {
    allowInsecureProtocol = true
    name = "lingaly"
    url = "http://124.223.63.208:8081/repository/MinecraftMaven-release/"
}



modImplementation include("bilibili.lingaly:easyfabricmodregister:<version>")

    implementation "com.googlecode.json-simple:json-simple:1.1.1"