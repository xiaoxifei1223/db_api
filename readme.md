# api 设计 
## 技术栈
springboot  

## 代码结构设计
有如下的文件夹，以及主程序入口，application.java 是主程序入口
- batch
- configuration
- constant
- controller
- entity
- model
- repository.digitalTwin
- service
- util
application.java  

## 功能设计  
我使用sql 来描述功能
```
CREATE TABLE solution_designs(
    design_id UUID PRIMARY KEY DEFAULT,
    trace_id UUID NOT NULL REFERENCES,
    req_id UUID NOT NULL REFERENCES,

    design_overview TEXT NOT NULL,
    design_metadata TEXT NOT NULL,
    affected_application_id JSONB NOT NULL，
    design_url TEXT NOT NULL,
    system_impact_analysis TEXT,
    security_considerations TEXT,
    performance_considerations TEXT,
    upstream_dependencies JSONB,
    downstream_dependencies JSONB,
    is_ai_generated BOOLEAN DEFAULT FALSE,
    design_agent_id VARCHAR(100),
    generated_at TIMESTAMP,

    architect_id VARCHAR(50) REFERENCES users(user_id),
    review_status VARCHAR(50) DEFAULT 'PENDING',
    reviewed_at TIMESTAMP,
    review_comments TEXT,
    review_evidence TEXT,

    github_commit_ids JSONB,
    github_file_paths JSONB,
    github_file_url JSONB,

    approver_id VARCHAR(50),
    approved_at TIMESTAMP,
    signoff_document_url VARCHAR(500),

    created_by VARCHAR(50) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_by VARCHAR(50),
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    create_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    update_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP 
)
```
第二个表

```
CREATE TABLE AIModelIssues(
    event_id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    solution_id UUID REFERENCES solution_designs(solution_id),
    design_id UUID REFERENCES system_designs(design_id),
    test_case_id UUID REFERENCES test_case(test_case_id),
    work_item_id UUID REFERENCES work_items(item_id),
    req_id UUID REFERENCES requirements(req_id),
    deployment_id UUID REFERENCES deployments(deployment_id),
    trace_id UUID NOT NULL REFERENCES sdlc_trace_root(trace_id),
    artifactType VARCHAR(255),
    artifactDescription TEXT,
    sourceRequirement TEXT,
    designReference TEXT,
    expectedCode TEXT,
    actualCodeStatus VARCHAR(255),
    issueType VARCHAR(255),
    lineageStatus VARCHAR(255),
    codeImplementation TEXT,
    lineage VARCHAR(255),
    createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP
)
```