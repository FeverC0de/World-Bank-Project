# WorldBank IA

Desktop application that visualizes World Bank economic indicators (GDP, population, inflation, exports, health metrics) with JFreeChart charts and auto-detects your country via IP geolocation.

## Features

- Auto-detects user's country using [MaxMind GeoLite2](https://dev.maxmind.com/geoip/)
- Queries local MongoDB for country-specific indicator data
- Interactive bar charts and time-series via JFreeChart
- Custom styled Swing UI components
- System tray "Did you know?" fact notifications

## Requirements

- Java 8+
- MongoDB running on `localhost:27017` with database `WorldBank`
- MaxMind GeoLite2 City database (`.mmdb` file)
- External JARs (not bundled):
  - `mongo-java-driver`
  - `geoip2`
  - `jfreechart`
  - `swingx`

## Project Structure

```
src/
├── backend/      # Database queries, IP geolocation, facts generator
├── ui/           # Swing screens: Dashboard, Data view, menus
└── components/   # Custom styled Swing components
docs/             # Design documents and documentation (PDFs)
```

## Build

Open in NetBeans or compile manually with all JARs on the classpath. A `pom.xml` / `build.gradle` is planned for future releases.

## License

MIT
