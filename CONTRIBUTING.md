# Contributing to LibreHealth Radiology

## Resources for Getting Started

Your contributions are what will make this project a great addition to the LibreHealth ecosystem.

Since this project is an LibreHealth module we try to stick to the best practices developed by the LibreHealth community.

## Reporting Bugs

1. Please check to see if you are running a version of LibreHealth Toolkit compatible with this module [README](README#limitations)
2. Please check if you built this module from the latest commit to the master branch; the bug may already be resolved.
3. Please check https://gitlab.com/librehealth/lh-radiology/issues if the bug has already been filed; if not feel free to open one yourself.

### Bug report contents

To help developers understand the problem, please include as much information as possible:

1. A clear description of how to recreate the error, including any error messages shown.
2. The version of LibreHealth Toolkit you are using.
3. The commit you built the LibreHealth radiology module from which you have deployed into LibreHealth Toolkit.
4. The type and version of the database you are using.
5. If applicable, please copy and paste the full Java stack trace.
6. If you have already communicated with a developer about this issue, please provide their name.

## Requesting New Features

1. We encourage you to discuss your feature ideas with our community before creating a New Feature issue in our issue tracker. Please login to [LibreHealth Forums](https://forums.librehealth.io/), search for existing topics on the radiology project and if they fit to yours just join the discussion. If you do not find any topic related to your idea open up a new one.

2. Provide a clear and detailed explanation of the feature you want and why it's important to add.

3. If you're an advanced programmer, build the feature yourself (refer to the "Contributing (Step-by-step)" section below).

## Contributing (Step-by-step)

1. Find an issue you will like to work on.

2. [Fork the repo](https://docs.gitlab.com/ce/gitlab-basics/fork-project.html) on which you're working, clone your forked repo to your local computer, and set up the upstream remote:

        git clone git://gitlab.com/YourGitHubUserName/lh-radiology.git
        git remote add upstream https://gitlab.com/librehealth/lh-radiology.git

3. Checkout out a new local branch based on your master and update it to the latest. The convention is to name the branch after the current Gitlab issue number, e.g. RAD-123:

        git checkout -b RAD-123 master
        git clean -df
        git pull --rebase upstream master

 > Please keep your code clean. Name your branch after the Gitlab issue or other description of the work being done. If you find another bug, you want to fix while being in a new branch, please fix it in a separated branch instead.


4. Push the branch to your fork. Treat it as a backup.

        git push -u origin RAD-123

5. Code
 * follow the LibreHealth coding conventions, please read https://wiki.openmrs.org/display/docs/Coding+Conventions
   * take the existing code in the module as a guide as well!

 * add unit tests, we will not accept merge requests which lower the unit test coverage https://coveralls.io/github/openmrs/openmrs-module-radiology?branch=master
    * see https://wiki.openmrs.org/display/docs/Unit+Tests
    * see https://wiki.openmrs.org/display/docs/Unit+Testing+Conventions
    * see https://wiki.openmrs.org/display/docs/Module+Unit+Testing

 * follow the LibreHealth formatting/code style
    * you will need to configure Eclipse to use the [OpenMRSFormatter.xml](tools/src/main/resources/eclipse/OpenMRSFormatter.xml) provided by this module.
    * when you build this module *.java files will automatically be formatted by a formatter plugin. You can manually run the formatter plugin with ```mvn java-formatter:format```
    * for xml and javascript files use **control-shift-f** in Eclipse.
    * remove unused imports by using **control-shift-o** in Eclipse.


  > However, please note that **merge requests consisting entirely of style changes are not welcome on this project**. Style changes in the context of merge requests that also refactor code, fix bugs, improve functionality *are* welcome.

7. Commit

  For every commit please write a short (max 72 characters) summary in the first line followed with a blank line and then more detailed descriptions of the change. Use markdown syntax for simple styling. Please include any Gitlab issue numbers in your summary.
  
        git commit -m "RAD-123: Put change summary here (can be a ticket title)"

  **NEVER leave the commit message blank!** Provide a detailed, clear, and complete description of your commit!

8. Issue a Merge Request

  Before submitting a merge request, update your branch to the latest code.
  
        git pull --rebase upstream master

  If you have made many commits, we ask you to squash them into atomic units of work. Most of tickets should have one commit only, especially bug fixes, which makes them easier to back port.

        git checkout master
        git pull --rebase upstream master
        git checkout RAD-123
        git rebase -i master

  Make sure all unit tests still pass:

        mvn clean package

  Push changes to your fork:

        git push -f

  In order to make a merge request,
  * Navigate to the modules repository you just pushed to (e.g. https://gitlab.com/your-user-name//lh-radiology)
  * Click "Merge Request".
  * Write your branch name in the branch field (this is filled with "master" by default)
  * Click "Update Commit Range".
  * Ensure the changesets you introduced are included in the "Commits" tab.
  * Ensure that the "Files Changed" incorporate all of your changes.
  * Fill in some details about your potential patch including a meaningful title.
  * Click "Send merge request".


  Thanks for that -- we'll get to your merge request ASAP. We love merge requests!

## Responding to Feedback

  The LibreHealth team may recommend adjustments to your code. Part of interacting with a healthy open source community requires you to be open to learning new techniques and strategies; *don't get discouraged!* Remember: if the LibreHealth team suggests changes to your code, **they care enough about your work that they want to include it**, and hope that you can assist by implementing those revisions on your own.

  > Though we ask you to clean your history and squash commit before submitting a pull-request, please do not change any commits you've submitted already (as other work might be build on top).


### Interact with the community

* Chat live with developers on [chat](https://chat.librehealth.io/)
* Hang out with other community members at [LibreHealth Forums](https://forums.librehealth.io/)

### Additional Resources

* [General GitHub documentation](http://help.github.com/)
* [GitLab merge request documentation](https://docs.gitlab.com/ee/gitlab-basics/add-merge-request.html)
