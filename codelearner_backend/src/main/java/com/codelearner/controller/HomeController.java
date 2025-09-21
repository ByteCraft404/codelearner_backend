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

        return """
                <!doctype html>
                <html lang="en">
                <head>
                    <meta charset="utf-8">
                    <meta name="viewport" content="width=device-width, initial-scale=1">
                    <title>CodeLearner API — Status</title>
                    <style>
                        :root {
                            --bg: #0f172a; /* slate-900 */
                            --card: #0b1220;
                            --accent1: linear-gradient(135deg, #7c3aed 0%, #06b6d4 100%);
                            --muted: #94a3b8;
                        }
                        html, body {
                            height: 100%;
                            margin: 0;
                            font-family: Inter, ui-sans-serif, system-ui, -apple-system, "Segoe UI", Roboto, "Helvetica Neue", Arial;
                            background: radial-gradient(circle at 10% 10%, rgba(99,102,241,0.08), transparent 10%), var(--bg);
                            color: #e6eef8;
                        }
                        .wrap { min-height: 100vh; display: flex; align-items: center; justify-content: center; padding: 32px }
                        .card { background: linear-gradient(180deg, rgba(255,255,255,0.02), rgba(255,255,255,0.01)); border-radius: 16px; padding: 28px; max-width: 900px; box-shadow: 0 8px 30px rgba(2,6,23,0.6); border: 1px solid rgba(255,255,255,0.04) }
                        .header { display: flex; gap: 20px; align-items: center }
                        .logo { width: 76px; height: 76px; border-radius: 12px; display: grid; place-items: center; background: linear-gradient(135deg,#06b6d4,#7c3aed); font-weight: 700; color: #06202a; font-size: 30px }
                        h1 { margin: 0; font-size: 22px }
                        .sub { color: var(--muted); margin-top: 6px }

                        .status { margin-top: 18px; display: flex; gap: 12px; flex-wrap: wrap }
                        .badge { padding: 8px 12px; border-radius: 999px; background: linear-gradient(90deg, rgba(255,255,255,0.03), rgba(255,255,255,0.01)); border: 1px solid rgba(255,255,255,0.03); font-weight: 600 }
                        .badge.ok { color: #10b981 }
                        .badge.dev { color: #f59e0b }

                        .grid { display: grid; grid-template-columns: 1fr 240px; gap: 18px; margin-top: 20px }
                        .main-info { line-height: 1.6 }
                        ul.services { list-style: none; padding: 0; margin: 0; display: grid; gap: 10px }
                        ul.services li { padding: 12px; border-radius: 10px; background: linear-gradient(180deg, rgba(255,255,255,0.01), transparent); border: 1px solid rgba(255,255,255,0.02) }
                        .meta { font-size: 13px; color: var(--muted); margin-top: 12px }

                        .right-card { background: linear-gradient(180deg, rgba(255,255,255,0.01), transparent); padding: 16px; border-radius: 12px; border: 1px solid rgba(255,255,255,0.02) }
                        .time { font-weight: 700 }

                        footer { margin-top: 18px; color: var(--muted); font-size: 13px }

                        @media (max-width: 720px) {
                            .grid { grid-template-columns: 1fr }
                            .logo { width: 56px; height: 56px; font-size: 22px }
                        }
                    </style>
                </head>
                <body>
                <div class="wrap">
                    <div class="card">
                        <div class="header">
                            <div class="logo">CL</div>
                            <div>
                                <h1>CodeLearner API — Operational</h1>
                                <div class="sub">A robust backend powering authentication, course content, progress tracking, and integrations.</div>
                                <div class="status">
                                    <div class="badge ok">✅ Services: Authentication · Courses · Profiles</div>
                                    <div class="badge dev">⚡ Mode: Development</div>
                                    <div class="badge">📡 Endpoints: Responsive</div>
                                </div>
                            </div>
                        </div>

                        <div class="grid">
                            <div class="main-info">
                                <p>Welcome — the application layer is live and ready to accept API requests. Below are a few key service highlights and health indicators to help you verify runtime behavior quickly.</p>

                                <ul class="services">
                                    <li><strong>Health Check</strong> — <span class="meta">/actuator/health or custom /health endpoint returning HTTP 200</span></li>
                                    <li><strong>Authentication</strong> — <span class="meta">JWT-based login & registration (POST /auth)</span></li>
                                    <li><strong>Course Engine</strong> — <span class="meta">RESTful CRUD endpoints for courses, lessons and progress</span></li>
                                    <li><strong>Telemetry</strong> — <span class="meta">Request logging, metrics and latency tracing available</span></li>
                                </ul>

                                <p class="meta">Tip: Use a tool like curl or Postman to exercise the endpoints and inspect JSON responses. For automated pipelines, wire your CI to run the health-check and smoke tests after deployment.</p>

                                <footer>Server started at: <span class="time">""" + startedAt + """</span></footer>
                            </div>

                            <aside class="right-card">
                                <h3 style="margin:0 0 8px 0">Quick Actions</h3>
                                <p class="meta">Commands to try from your terminal</p>
                                <pre style="background:transparent;border-radius:8px;padding:10px;margin-top:8px;color:#cfe8ff;overflow:auto;font-size:13px">
curl -i http://localhost:8080/
curl -i http://localhost:8080/health
curl -X POST http://localhost:8080/auth/login -d '{"email":"you@domain.com","password":"secret"}' -H 'Content-Type: application/json'
                                </pre>

                                <div style="margin-top:12px;font-size:13px;color:var(--muted)">Version: <strong>1.0.0</strong></div>
                            </aside>
                        </div>
                    </div>
                </div>
                </body>
                </html>
                """;
    }
}
