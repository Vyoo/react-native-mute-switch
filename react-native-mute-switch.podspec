# react-native-mute-switch.podspec

require "json"

package = JSON.parse(File.read(File.join(__dir__, "package.json")))

Pod::Spec.new do |s|
  s.name         = "react-native-mute-switch"
  s.version      = package["version"]
  s.summary      = package["description"]
  s.description  = <<-DESC
                  react-native-mute-switch
                   DESC
  s.homepage     = "https://github.com/Vyoo/react-native-mute-switch"
  # brief license entry:
  s.license      = "MIT"
  # optional - use expanded license entry instead:
  # s.license    = { :type => "MIT", :file => "LICENSE" }
  s.authors      = { "Oleg" => "oleg@makeen.io" }
  s.platforms    = { :ios => "9.0" }
  s.source       = { :git => "https://github.com/Vyoo/react-native-mute-switch.git", :tag => "#{s.version}" }

  s.source_files = "ios/**/*.{h,c,cc,cpp,m,mm,swift}"
  s.requires_arc = true

  s.dependency "React"
  s.dependency 'AFNetworking', '~> 3.0'
  # s.dependency "..."
end

