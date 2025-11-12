Flipkart Mobile Search Automation
================================

I put together a quick Selenium script to automate the Flipkart login and mobile search flow. Below is a summary of what the run looked like on my machine.

<img src="image1.jpg" alt="Logo" width="200"/>


Test Environment
----------------
- Java 21 with Selenium bindings
- Chrome browser under automated control
- Workspace: `basic_selenium` project in Eclipse (package `seleniumautomation`)

Run Walkthrough
---------------
1. Launched the Flipkart login page — the driver confirmed the page was up.
2. Typed in the test mobile number and kicked off the OTP request.
3. Paused for 30 seconds so that the OTP could be entered manually on the phone.
4. Once the wait finished, continued the flow assuming the OTP step went through.
5. Searched Flipkart for “mobiles under 10000”.
6. Waited for the results grid to load and collected the first 24 product cards.
7. Wrote the captured product details to `C:\selenium\Flipkart_Mobiles.txt`.
8. Captured a screenshot of the results page for reference.

Outcome
-------
- Products found: 24
- Product list file: `C:\selenium\Flipkart_Mobiles.txt`
- Screenshot: stored alongside the output in the same run directory
- Console reported: “Test completed successfully — products extracted & screenshot captured.”

Next Steps
----------
If I need to re-run the test, I just have to update any credentials, verify the output paths exist, and execute `selenium1.java` again from Eclipse.


