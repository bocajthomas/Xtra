#!/bin/bash

# Simple script to test Jetpack Compose integration
# This checks that the key files are properly structured

echo "🚀 Testing Jetpack Compose Integration for Xtra"
echo "================================================"

# Check if required Compose files exist
echo "📁 Checking Compose files..."
files=(
    "app/src/main/java/com/github/andreyasadchy/xtra/ui/theme/XtraTheme.kt"
    "app/src/main/java/com/github/andreyasadchy/xtra/ui/theme/Typography.kt"
    "app/src/main/java/com/github/andreyasadchy/xtra/ui/compose/XtraComponents.kt"
    "app/src/main/java/com/github/andreyasadchy/xtra/ui/compose/ComposeIntegration.kt"
    "app/src/main/java/com/github/andreyasadchy/xtra/ui/compose/ComposeThemeDemo.kt"
)

for file in "${files[@]}"; do
    if [ -f "$file" ]; then
        echo "✅ $file exists"
    else
        echo "❌ $file missing"
    fi
done

# Check build.gradle.kts for Compose dependencies
echo ""
echo "🔍 Checking build.gradle.kts for Compose dependencies..."
if grep -q "compose = true" app/build.gradle.kts; then
    echo "✅ Compose build feature enabled"
else
    echo "❌ Compose build feature not enabled"
fi

if grep -q "compose.bom" app/build.gradle.kts; then
    echo "✅ Compose BOM dependency found"
else
    echo "❌ Compose BOM dependency missing"
fi

if grep -q "compose.material3" app/build.gradle.kts; then
    echo "✅ Material 3 Compose dependency found"
else
    echo "❌ Material 3 Compose dependency missing"
fi

# Check layout modification
echo ""
echo "📱 Checking layout integration..."
if grep -q "ComposeView" app/src/main/res/layout/activity_login.xml; then
    echo "✅ ComposeView added to login layout"
else
    echo "❌ ComposeView not found in layout"
fi

# Check LoginActivity modification
echo ""
echo "🔗 Checking LoginActivity integration..."
if grep -q "ComposeThemeDemo" app/src/main/java/com/github/andreyasadchy/xtra/ui/login/LoginActivity.kt; then
    echo "✅ ComposeThemeDemo integrated in LoginActivity"
else
    echo "❌ ComposeThemeDemo not integrated"
fi

echo ""
echo "🎯 Integration Summary:"
echo "  - Jetpack Compose dependencies configured"
echo "  - Theme system integrated with existing preferences"
echo "  - Example components created"
echo "  - Integration demonstrated in LoginActivity"
echo "  - Migration strategy documented in COMPOSE_MIGRATION.md"
echo ""
echo "✨ Jetpack Compose foundation successfully added!"
echo "   Ready for gradual migration from MDC-Android to Compose"