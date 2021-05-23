# coms3009a-bankingsystem
<!--  [![Build Status](https://travis-ci.com/NeoMaja/coms-3009a-bankingsystem.svg?token=KRJyGJdtUy45xqsuRezk&branch=main)](https://travis-ci.com/NeoMaja/coms-3009a-bankingsystem) -->
[![codecov](https://codecov.io/gh/NeoMaja/coms-3009a-bankingsystem/branch/main/graph/badge.svg?token=WX9OXZK4WD)](https://codecov.io/gh/NeoMaja/coms-3009a-bankingsystem)

[![Circle CI](https://circleci.com/gh/NeoMaja/coms-3009a-bankingsystem.svg?style=shield)](https://app.circleci.com/pipelines/github/NeoMaja/coms-3009a-bankingsystem) 

**Software Architecture**

The banking app is written in Java. It is designed to optimize the traditional banking system by speeding up processes like opening a bank account, balance enquiry, transactions and other menial tasks. It accomplishes this by the use of well-thought-out algorithms that prioritize security.

**Features:**

- Secure password interface
- Login page
- Registration page with input validation checks
- Verification status screen
- Client bank account screen
- Admin login key screen for security purposes
- Verification screen where admin verifies users
- A screen with list of users who have been verified

**Requirements:**

- Android API 16 & above all

**How to Installing:**

- Click on the APK

**How does it work?**

- On opening the App, you are taken to the login screen.
 - If you aren't registered on the app yet, you can choose the option to create a new account. You will then be taken to a screen where you have to choose whether you are registering as an admin or a client.
- If you click on admin you are taken to the admin registration screen on which you should only be able to register after you have entered a registration admin key.
- After registering, you should be able to see your admin key before being taken to a screen where you can verify users.
- If you logged in from the get go as an admin, you will be taken to a page which requires you to input your unique admin key generated at registration.
- From the admin key screen, the admin is either led to the screen that will show a list of users awaiting verification or a screen showing a list of users that are already in the system.
- On the admin verification screen, the admin is able to verify users.
- If you chose the client option, you are taken to the client registration screen.
- After registration you are taken to either the verification screen or your profile/account screen. You see your pending verification status only if you haven't been verified.
- If you logged in as a client from the get go (having already registered prior), you will input your login credentials after which you should be taken to a verification status page where you will view your verification status.
- If you've already been verified by  the admin, you should be taken to your account where you can see your account details.
