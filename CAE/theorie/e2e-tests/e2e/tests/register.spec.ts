import { test, expect } from "@playwright/test";
import { faker } from "@faker-js/faker";
import { goFromHomePageToRegisterPage, registerWith } from "./helper";

test.describe("Register", () => {
  test.beforeEach(async ({ page }) => {
    goFromHomePageToRegisterPage(page);
  });

  test("TC1.1: should register a new user when valid username & password", async ({
    page,
  }) => {
    const username = faker.internet.username();
    const password = faker.internet.password();
    await registerWith(page, username, password);

    await expect(page.getByText(`Hello dear ${username}`)).toBeVisible();
  });

  test("TC2.1: should not register a new user when existing username", async ({
    page,
  }) => {
    const username = "farina";
    const password = "farinapwd";
    await registerWith(page, username, password);

    await expect(page.getByText(`Hello dear ${username}`)).toBeVisible();

    await page.getByText("se déconnecter").click();

    goFromHomePageToRegisterPage(page);

    await registerWith(page, username, password);

    // Check in the console for the error message
    const message = await page.waitForEvent(
      "console",
      (msg) => msg.type() === "error"
    );

    // Check for specific error message
    expect(message.text()).toContain("409");
  });
});
