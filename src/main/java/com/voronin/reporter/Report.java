package com.voronin.reporter;

class Report {
    private final String content;
    private final String uri;
    private final String engine;

    Report(String content, String uri, String engine) {
        this.content = content;
        this.uri = uri;
        this.engine = engine;
    }

    String getContent() {
        return content;
    }

    String getURI() {
        return uri;
    }

    String getEngine() {
        return engine;
    }
}
