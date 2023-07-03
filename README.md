## Micronaut 3.8.8 Documentation

- [User Guide](https://docs.micronaut.io/3.8.8/guide/index.html)
- [API Reference](https://docs.micronaut.io/3.8.8/api/index.html)
- [Configuration Reference](https://docs.micronaut.io/3.8.8/guide/configurationreference.html)
- [Micronaut Guides](https://guides.micronaut.io/index.html)
---

## Feature HTTP Client Documentation

- [Micronaut HTTP Client documentation](https://docs.micronaut.io/latest/guide/index.html#httpClient)

---

## Micronaut POC


## Get Started
- Clone the project from git
- cd to project directory
- run ``mvn clean install`` to install the dependencies and build the application
- Update config under the sections `app-config` and `ehcache` from the file `application.yml`
  - update `ehcache.storage-path` with the absolute path
  - update `publication-base-path` with the absolute path 
  - An example configuration of `app-config`:
  ```yaml
  app-config:
    session-cookie-name: DEV_IBFD_SESSION
    publication-base-path: D:\Workshop\poc\micronaut-otcc-poc\teststorage\publications
    session-manager-url: http://development7.ibfd.org:9000/sm.fpl
    limaserver-base-url: http://development7.ibfd.org:9080
    linkresolver-base-url: https://dev-research.ibfd.org/linkresolver/resolve
    linkresolver-use-post: true
    regional-pdf-xsl-url: jar:https://nexus3.ibfd.org/repository/ibfd-releases/org/ibfd/common-fop-pdf/1.0.54/common-fop-pdf-1.0.54.jar!/otcc-pdf-converter.xsl
  ``` 
  - An example configuration of `ehcache`:
  ```yaml
  ehcache:
    storage-path: D:\Workshop\poc\micronaut-otcc-poc\teststorage\caches
    caches:
      renderer:
        disk:
          max-size: 1Gb
  ```
  - Note: a `teststorage` folder which already resides within this repository
- Run project from terminal using command: `mvn mn:run`
- Now open `localhost:8080` on browser. You will get some test links for testing purpose.
- Add `DEV_IBFD_SESSION` cookie before you hit any of the link listed.
  You can get the cookie value from `dev-research.ibfd.org`.
- You will get the output html or pdf result.


