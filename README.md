
### Meet the Interactive Feature Trainer

This interactive course will guide you through a lot of IDE workflows and use cases. It comes with many helpful action shortcuts. You don't need to memorize all of them. Instead, try them and take into your service the most useful ones. Since during your everyday work, you might encounter new problems, revisit this course and give other features a second try. Stay tuned for new lessons helping you to be more productive with IntelliJ IDEA.

##### How to use the course

The `Learn` tool window contains several modules with the corresponding sets of lessons that you can take in any order. When you finish studying a lesson, it will be marked as completed. You can use a completed lesson for your reference or restart it if you want to refresh your knowledge.

Even though a lesson looks familiar to you, give it a try: you may discover helpful examples and unknown shortcuts of the features you actively use!

##### Get started

You are likely reading this `README.md` during the learning project initialization. Usually, `indexing` is the most time-consuming operation during project initialization. The IDE will need to collect information about your environment, project, libraries, and construct internal representation. It is needed for fast navigation and search. The `Navigation` module in this course will show how to use it.

##### Manage shortcuts

Some shortcuts in this course may conflict with the system or other application shortcuts. In this case, you can click the problematic shortcut in the text of the lesson. The opened popup will show you the action name and alternative shortcuts. You will be able to apply the action or assign a new shortcut.

If there is another issue, try to restart the lesson or report the bug in [our bug tracker](https://youtrack.jetbrains.com/issues/IFT).

##### Get back to your project

To return to the welcome screen, just close the project window. Alternatively, you can select `File | Close Project` from the main menu. Also, you can use the `File` menu to open or create new projects. By default, the `Learn` tool window is hidden. You can open it by selecting `Help | Learn IDE Features` from the main menu.

We will appreciate you leaving your feedback about this learning course!
  

### Best practices: Using a main branch alongside or instead of master on GitHub

Many repositories are moving from master to main as the primary branch. The simplest, safest practice is to have a single long‑lived default branch. If you currently have both master and main, prefer one as the default (ideally main) and treat the other as deprecated or a temporary mirror during migration.

Recommended approaches
- Prefer a single default branch
  - Pick main as the default branch in GitHub repo settings (Settings → Branches → Default branch).
  - Do not keep two active long‑lived branches (master and main) diverging; this causes confusion, broken PRs, and drift.
- If you must keep both temporarily (for compatibility)
  - Mark master as deprecated in README and repository description.
  - Protect both branches (Settings → Branches → Branch protection): require PR reviews, status checks, and disallow direct pushes.
  - Set up a one‑way synchronization from main → master (e.g., a scheduled GitHub Actions workflow) so master mirrors main until consumers migrate.
  - Sunset the mirror with a clear deadline.
- Communicate and coordinate
  - Announce the change, timeline, and actions to contributors and downstream consumers.
  - Update contributing guides, templates, and tooling that refers to master.

How to migrate from master to main (recommended)
1) Locally rename and push
   - git checkout master
   - git pull
   - git branch -m master main
   - git push -u origin main
2) In GitHub
   - Set main as the default branch (Settings → Branches).
   - Update branch protections to apply to main.
3) Update references
   - Update CI/CD workflows, badges, release scripts, deployment environments, and any docs that reference master.
   - Update PR templates and CODEOWNERS, if they target master.
4) Optionally keep a temporary mirror
   - Create a workflow that force-updates master from main on push to main (for a limited time):

     name: Mirror main to master
     on:
       push:
         branches: [ main ]
     jobs:
       mirror:
         runs-on: ubuntu-latest
         steps:
           - uses: actions/checkout@v4
             with:
               fetch-depth: 0
           - name: Push to master
             run: |
               git config user.name "github-actions"
               git config user.email "actions@github.com"
               git checkout -B master
               git reset --hard origin/main
               git push --force origin master

5) Clean up when ready
   - Remove the mirror workflow.
   - Delete master in GitHub once all consumers have switched (Settings → Branches → Delete branch) or leave it locked with a final README note pointing to main.

CI/CD and tooling checklist
- GitHub Actions: update on: push/pull_request branches from master to main.
- External integrations: coverage, code quality, release pipelines, environments.
- Protected environments: adjust rules that reference master.
- Default base branch for PRs: set to main and update any bots or automation.

FAQ
- Can I keep both branches permanently? Not recommended. It increases maintenance burden and risk of divergence. If required by legacy systems, keep master as a read-only mirror of main and document it clearly.
- Do I need to rewrite history? No. A branch rename and reference updates are enough in most cases.
- Will open PRs to master break? Retarget them to main. GitHub offers bulk retargeting filters in the Pull Requests tab.
