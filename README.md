# Mensch-aergere-dich-nicht

graph TD
    %% Startpunkt
    Start((Mitarbeiter hat Idee)) --> Analyst[<b>Rolle: Analyst</b><br/>Anforderungs-Interview]

    %% Phase 1: Analyse
    subgraph "Phase 1: Scoping"
    Analyst --> Check{Alle Infos<br/>vorhanden?}
    Check -- Nein --> Analyst
    Check -- Ja --> Requirements[Strukturiertes Anforderungsprofil]
    end

    %% Phase 2: Architektur
    subgraph "Phase 2: Entwurf"
    Requirements --> Architekt[<b>Rolle: Architekt</b><br/>Konzeptions-Engine]
    RAG[(Wissensdatenbank<br/>& Design Patterns)] -.-> Architekt
    Architekt --> Draft[Erster Konzept-Entwurf<br/>inkl. Diagramme]
    end

    %% Phase 3: Ablage & Iteration
    subgraph "Phase 3: Finalisierung & Speicher"
    Draft --> SharePoint[[<b>MCP Server</b><br/>SharePoint Ablage]]
    SharePoint --> Feedback{Mitarbeiter<br/>zufrieden?}
    Feedback -- Nein (Refinement) --> Architekt
    Feedback -- Ja --> End((Finales Konzept))
    end

    %% Styles
    style Analyst fill:#f9f,stroke:#333,stroke-width:2px
    style Architekt fill:#bbf,stroke:#333,stroke-width:2px
    style SharePoint fill:#dfd,stroke:#333,stroke-width:2px
    style RAG fill:#fff4dd,stroke:#d4a017,stroke-width:2px
