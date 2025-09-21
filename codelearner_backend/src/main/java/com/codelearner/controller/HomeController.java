package com.codelearner.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
public class HomeController {

    @GetMapping(value = "/", produces = MediaType.TEXT_HTML_VALUE)
    public String home() {
        String startedAt = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        String html = """
                <!doctype html>
                <html lang="en">
                <head>
                    <meta charset="utf-8">
                    <meta name="viewport" content="width=device-width, initial-scale=1">
                    <title>CodeLearner API — Status</title>
                    <style>
                        body { font-family: Arial, sans-serif; background:#0f172a; color:#e6eef8; }
                        .wrap { display:flex; justify-content:center; align-items:center; height:100vh; }
                        .card { background:#0b1220; padding:24px; border-radius:12px; max-width:800px; }
                        h1 { margin:0; font-size:24px; }
                        .sub { color:#94a3b8; margin-top:6px; }
                        .status { margin-top:12px; }
                        .badge { display:inline-block; margin:4px; padding:6px 12px; border-radius:999px; font-weight:600; }
                        .ok { color:#10b981; }
                        .dev { color:#f59e0b; }
                        footer { margin-top:16px; font-size:13px; color:#94a3b8; }
                    </style>
                </head>
                <body>
                <div class="wrap">
                    <div class="card">
                        <h1>CodeLearner API — Operational</h1>
                        <div class="sub">Backend is live and ready to handle requests.</div>
                        <div class="status">
                            <span class="badge ok">✅ Services Running</span>
                            <span class="badge dev">⚡ Development Mode</span>
                            <span class="badge">📡 Endpoints Responsive</span>
                        </div>
                        <footer>Server started at: %s</footer>
                    </div>
                </div>
                </body>
                </html>
                """;

        return String.format(html, startedAt);
    }
}
