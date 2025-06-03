# plansource-automation
---

## 🚀 How to Set Up & Run

### ✅ Prerequisites

- Java 8+
- Maven 3.6+
- IntelliJ IDEA or any Java IDE
- Chrome browser

---

### ⚙️ Installation

1. **Clone the repository:**

```bash
git clone https://github.com/abhishekmorwal59/plansource-automation.git
cd plansource-automation

mvn clean install

```
2. **Run Tests**

From IntelliJ (Recommended)
Right-click on textng.xml → Run.

3. **Reporting with Allure**

Install Allure CLI (macOS)

```bash
brew install allure
```

Report open automatically after execution

4. **Sample Test Flow**

Login to Portal

Create New Employee

Start Enrollment

Add Dependents

Select Medical + Voluntary Plans (UI)

Enroll in Dental Plan (API)

Proceed to Checkout

Download and Validate Confirmation PDF

5. **Configuration**
   
url=https://partner-dev-benefits.plansource.com
username=your-username
password=your-password

6. **Screenshot**
   
 Take screenshot if testcase failed 


