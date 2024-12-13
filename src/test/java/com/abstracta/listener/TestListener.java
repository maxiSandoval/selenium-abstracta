package com.abstracta.listener;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.abstracta.utils.Constants;

public class TestListener implements ITestListener{

    @Override
    public void onTestFailure(ITestResult result) {
        // Verificar si el WebDriver está correctamente configurado en el contexto
        WebDriver driver = (WebDriver) result.getTestContext().getAttribute(Constants.DRIVER);
        
        if (driver == null) {
            System.out.println("WebDriver no encontrado en el contexto.");
            return; // Si no se encuentra el WebDriver, no se puede tomar la captura de pantalla
        }

        // Tomar la captura de pantalla como base64
        String screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);

        // Crear el formato HTML para mostrar la imagen en el reporte
        String htmlImageFormat = "<img width='700px' src='data:image/png;base64,%s' />";
        String htmlImage = String.format(htmlImageFormat, screenshot);

        // Incluir la imagen en el reporte de TestNG
        Reporter.log(htmlImage);
    }
    
}
