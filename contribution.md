# Contribution Guide

Thank you for considering a contribution! This document outlines a lightweight, practical workflow so you can get changes merged smoothly.

## Before you start
- Ensure you can build and run tests locally.
- Use the repository’s default branch as your base. Most projects use `main`. If this repo still uses `master`, follow the guidance in README about migrating/targeting the correct default branch.

## Getting started
1. Fork the repository and clone your fork.
2. Add the upstream remote:
   - git remote add upstream <original_repo_url>
3. Create a feature branch from the default branch:
   - git checkout main   # or master if this repo still uses it
   - git pull --ff-only
   - git checkout -b feat/short-description

## Branch naming
- feat/short-description for new features
- fix/short-description for bug fixes
- docs/short-description for documentation changes
- chore/short-description for maintenance tasks

## Commit messages
- Use clear, imperative messages (e.g., "Add input validation for null arrays").
- Group related changes in a single commit when reasonable.
- Reference issues when applicable: "Fix #123: handle empty input".

## Code style and scope
- Keep PRs small and focused. Separate unrelated changes.
- Java: follow standard conventions; prefer meaningful names and small methods.
- Include comments where intent isn’t obvious.
- Do not commit build outputs (e.g., files under `out/`).

## Tests
- Add or update tests for new behavior.
- Ensure all tests pass locally before opening a PR.

## Pull request checklist
- Rebased on the latest default branch (main or master as applicable).
- CI passes (build, tests, linters if configured).
- Includes tests and documentation updates when needed.
- PR description explains the change, motivation, and any breaking impact.

## Reporting issues
- Search existing issues first to avoid duplicates.
- Provide steps to reproduce, expected vs actual behavior, logs, and environment details.

## Security disclosures
- Please do not open public issues for security vulnerabilities.
- Report privately to the maintainer: medina1camilo@gmail.com.

## License
By contributing, you agree that your contributions will be licensed under the same license as the project.
